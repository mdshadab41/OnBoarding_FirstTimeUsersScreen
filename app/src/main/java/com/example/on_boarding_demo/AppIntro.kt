package com.example.on_boarding_demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.on_boarding_demo.AppIntro.Companion.MAX_STEP
import com.example.on_boarding_demo.databinding.IntroAppContentBinding
import com.example.on_boarding_demo.databinding.IntroAppDesignBinding
import com.google.android.material.tabs.TabLayoutMediator

class AppIntro: Fragment() {
private var _binding: IntroAppContentBinding? = null

    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = IntroAppContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //----------------------------------------
        binding.viewPager2.adapter = AppIntroViewPager2Adapter()

        //-----------------------------------------------------
        TabLayoutMediator(binding.tabLayout, binding.viewPager2){
            tab, position ->
        }.attach()

        //-----------------------------------------------------
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position== MAX_STEP-1){
                    binding.btnNext.text = getString(R.string.intro_get_started)
                    binding.btnNext.contentDescription = getString(R.string.intro_get_started)
                }else{
                    binding.btnNext.text = getString(R.string.intro_next)
                    binding.btnNext.contentDescription = getString(R.string.intro_next)
                }

            }
        })

        //-----------------------------------------------------
        binding.btnSkip.setOnClickListener {
            //todo
            findNavController().navigateUp()
        }
        //----------------------------------------
        binding.btnNext.setOnClickListener {
            if (binding.btnNext.text.toString() == getString(R.string.intro_get_started)){
                //todo
                findNavController().navigateUp()
            }
            else{
                //to change  current page  - on  click  "Next Button"
                val current = (binding.viewPager2.currentItem) + 1
                binding.viewPager2.currentItem = current


                //update button text
                if (current >= MAX_STEP-1){
                    binding.btnNext.text = getString(R.string.intro_get_started)
                    binding.btnNext.contentDescription = getString(R.string.intro_get_started)
                }else{
                    binding.btnNext.text = getString(R.string.intro_next)
                    binding.btnNext.contentDescription = getString(R.string.intro_next)
                }
            }
        }





    }
    companion object{
        const val MAX_STEP = 3
    }
}



class AppIntroViewPager2Adapter : RecyclerView.Adapter<PagerVH2>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH2 {
        return PagerVH2(
            IntroAppDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun getItemCount(): Int = MAX_STEP


    //binding the screen with the view
    override fun onBindViewHolder(holder: PagerVH2, position: Int) = holder.itemView.run {
        with(holder){

            if (position == 0){
                bindingDesign.introTitle.text = context.getString(R.string.intro_title_1)
                bindingDesign.introImage.setImageResource(R.drawable.imag_1)
            }
            if (position == 1){
                bindingDesign.introTitle.text = context.getString(R.string.intro_title_2)
               // bindingDesign.introDescription.text = context.getString(R.string.intro_description_2)
                bindingDesign.introImage.setImageResource(R.drawable.img_2)
            }
            if (position == 2){
                bindingDesign.introTitle.text = context.getString(R.string.intro_title_3)
               // bindingDesign.introDescription.text = context.getString(R.string.intro_description_3)
                bindingDesign.introImage.setImageResource(R.drawable.img_3)
            }

        }


    }

}

class PagerVH2(val bindingDesign: IntroAppDesignBinding): RecyclerView.ViewHolder(bindingDesign.root)
